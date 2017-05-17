package com.cj.lambdautils;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 
 * @author Daniel Brice with some help from dr0n.
 * Taken from https://gist.github.com/friedbrice/10d63d36814f42ecab4351458e747e4f with permission, but I would have even without.
 */
public abstract class Either<L, R> {

    public final static <L0, R0> Either<L0, R0> left(L0 value) {
    	if (value == null) return Either.neither(); 
        return new Left<L0, R0>(value);
    }

    public final static <L0, R0> Either<L0, R0> right(R0 value) {
    	if (value==null) return Either.neither(); 
        return new Right<L0, R0>(value);
    }
    
    public final static <L0, R0> Either<L0, R0> neither() {
        return new Neither<L0, R0>();
    }
    

    public abstract <T0> T0 fold(Function<L, T0> leftCallback, Function<R, T0> rightCallback, Supplier<T0> fallback);

    public final boolean isNeither() {
    	return fold(
    			(L l) -> false,
    			(R r) -> false,
    			()    -> true
		);
    }
    
    public final Optional<L> getLeft() {
        return fold(
                (L l) -> Optional.<L>ofNullable(l),
                (R r) -> Optional.<L>empty(),
                ()    -> Optional.<L>empty() 
        );
    }
    
	public boolean isLeft() {
		return getLeft().isPresent();
	}

    public final <T0> Either<T0, R> mapLeft(Function<L, T0> f) {
        return fold(
                (L l) -> Either.<T0, R>left(f.apply(l)),
                (R r) -> Either.<T0, R>right(r),
                ()    -> Either.<T0, R>neither()
        );
    }

    public final <T0> Either<T0, R> flatMapLeft(Function<L, Either<T0, R>> k) {
        return fold(
                (L l) -> k.apply(l),
                (R r) -> Either.<T0, R>right(r),
                ()    -> Either.<T0, R>neither()
        );
    }
    
    public final L leftOrElse(L fallback){
        return fold(
                (L l) -> l,
                (R r) -> fallback,
                ()    -> fallback
        );
    }

    public final Optional<R> getRight() {
        return fold(
                (L l) -> Optional.<R>empty(),
                (R r) -> Optional.<R>ofNullable(r),
                ()    -> Optional.<R>empty()
        );
    }

	public boolean isRight() {
		return getRight().isPresent();
	}

    public final <T0> Either<L, T0> mapRight(Function<R, T0> f) {
        return fold(
                (L l) -> Either.<L, T0>left(l),
                (R r) -> Either.<L, T0>right(f.apply(r)),
                ()    -> Either.<L, T0>neither()
        );
    }

    public final <T0> Either<L, T0> flatMapRight(Function<R, Either<L, T0>> k) {
        return fold(
                (L l) -> Either.<L, T0>left(l),
                (R r) -> k.apply(r),
                ()    -> Either.<L, T0>neither()
        );
    }
    
    public final R rightOrElse(R fallback){
        return fold(
                (L l) -> fallback,
                (R r) -> r,
                ()    -> fallback
        );
    }
    
    @SuppressWarnings("rawtypes")
	@Override public final boolean equals(Object otherUntyped) {
    	if ( !(otherUntyped instanceof Either)) return false;
    	Either other = (Either) otherUntyped;
    	return fold(
    			(L l) -> other.getLeft().equals(Optional.of(l)),
    			(R r) -> other.getRight().equals(Optional.of(r)),
    			()    -> other.isNeither()
    	);
    }

    private final static class Left<L0, R0> extends Either<L0, R0> {
        L0 value;

        Left(L0 value) {
            this.value = value;
        }

        @Override
        public <T0> T0 fold(Function<L0, T0> leftCallback, Function<R0, T0> rightCallback, Supplier<T0> fallback) {
            return leftCallback.apply(this.value);
        }
    }

    private final static class Right<L0, R0> extends Either<L0, R0> {
        R0 value;

        Right(R0 value) {
            this.value = value;
        }

        @Override
        public <T0> T0 fold(Function<L0, T0> leftCallback, Function<R0, T0> rightCallback, Supplier<T0> fallback) {
            return rightCallback.apply(this.value);
        }
    }
    
    private final static class Neither<L0, R0> extends Either<L0, R0> {
    	@Override
    	public <T0> T0 fold(Function<L0, T0> leftCallback, Function<R0, T0> rightCallback, Supplier<T0> fallback) {
    		return fallback.get();
    	}
    }

}