package com.nishtahir

import androidx.ui.util.fastForEach
import androidx.ui.util.fastMap
import org.openjdk.jmh.annotations.*
import java.util.concurrent.*

@State(Scope.Benchmark)
@Warmup(iterations = 5)
@Measurement(iterations = 10, time = 3, timeUnit = TimeUnit.SECONDS)
class TestBenchmark {

    @Param("10", "1000", "100000", "10000000")
    var size: Int = 0

    private lateinit var list: List<Int>

    @Setup
    fun setUp() {
        list = List(size) { (0..10).random() };
    }

    @Benchmark
    fun mapList(): List<Int> {
        return list.map { it * it }
    }

    @Benchmark
    fun fastMapList(): List<Int> {
        return list.fastMap { it * it }
    }

    @Benchmark
    fun forEachList(): Int {
        var count = 0
        list.forEach { count += it }
        return count
    }

    @Benchmark
    fun fastForEachList(): Int {
        var count = 0
        list.fastForEach { count += it }
        return count
    }
}