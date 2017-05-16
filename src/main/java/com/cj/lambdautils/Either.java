package com.cj.lambdautils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

interface Either<L, R> {
	public static <L, R> Either<L, R> left(L value) {
		return new Left<>(value);
	}

	public static <L, R> Either<L, R> right(R value) {
		return new Right<>(value);
	}

	public default boolean isRight() {
		return fold(l -> false, r -> true);
	}

	public default boolean isLeft() {
		return fold(l -> true, r -> false);
	}

	public <T> T fold(Function<? super L, ? extends T> lFunc, Function<? super R, ? extends T> rFunc);

	public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> lFunc);

	public <T> Either<L, T> mapRight(Function<? super R, ? extends T> rFunc);

	public void apply(Consumer<? super L> lFunc, Consumer<? super R> rFunc);

	public Optional<R> optionRight();

	public Optional<L> optionLeft();

	class Left<L, R> implements Either<L, R> {
		private final L value;

		public Left(L value) {
			this.value = value;
		}

		public <T> T fold(Function<? super L, ? extends T> lFunc, Function<? super R, ? extends T> rFunc) {
			return lFunc.apply(value);
		}

		public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> lFunc) {
			return new Left<>(lFunc.apply(value));
		}

		public <T> Either<L, T> mapRight(Function<? super R, ? extends T> rFunc) {
			return new Left<>(value);
		}

		public void apply(Consumer<? super L> lFunc, Consumer<? super R> rFunc) {
			lFunc.accept(value);
		}

		public Optional<R> optionRight() {
			return Optional.empty();
		}

		public Optional<L> optionLeft() {
			return Optional.of(value);
		}
	}

	class Right<L, R> implements Either<L, R> {
		private final R value;

		public Right(R value) {
			this.value = value;
		}

		public <T> T fold(Function<? super L, ? extends T> lFunc, Function<? super R, ? extends T> rFunc) {
			return rFunc.apply(value);
		}

		public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> lFunc) {
			return new Right<>(value);
		}

		public <T> Either<L, T> mapRight(Function<? super R, ? extends T> rFunc) {
			return new Right<>(rFunc.apply(value));
		}

		public void apply(Consumer<? super L> lFunc, Consumer<? super R> rFunc) {
			rFunc.accept(value);
		}

		public Optional<R> optionRight() {
			return Optional.of(value);
		}

		public Optional<L> optionLeft() {
			return Optional.empty();
		}
	}
}