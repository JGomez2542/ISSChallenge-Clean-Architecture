package com.example.jasongomez.isschallenge.domain.common

import io.reactivex.ObservableTransformer

/**
 * Just a simple ObservableTransformer. Allows us to control on which threads the use case runs. This is especially useful when writing tests
 * since we can run the code synchronously.
 */
abstract class Transformer<T> : ObservableTransformer<T, T>