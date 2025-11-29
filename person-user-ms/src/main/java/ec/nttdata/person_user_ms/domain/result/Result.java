package ec.nttdata.person_user_ms.domain.result;


import ec.nttdata.person_user_ms.domain.exception.BadResultException;

import java.util.function.Function;

public sealed interface Result<T> permits Result.Success, Result.Failure {
    record Success<T>(T value) implements Result<T>{}
    record Failure<T>(ResultError error) implements Result<T>{ }

    default boolean isSuccess() {
        return this instanceof Result.Success<T>;
    }

    default boolean isFailure() {
        return this instanceof Result.Failure<T>;
    }

    default T getOrElse(T defaultValue) {
        return this instanceof Result.Success<T> (T value)
                ? value
                : defaultValue;
    }



    default <R> R ifSuccess(Function<T, R> onSuccess) {
        if(this instanceof Result.Success<T>(T value)) {
            return onSuccess.apply(value);
        }
        throw new BadResultException("Action was not success");
    }

    default <R> R fold(Function<T, R> onSuccess, Function<ResultError, R> onFailure) {
        return switch (this) {
            case Success<T> (T value) -> onSuccess.apply(value);
            case Failure<T> (ResultError error) -> onFailure.apply(error);
        };
    }

}

