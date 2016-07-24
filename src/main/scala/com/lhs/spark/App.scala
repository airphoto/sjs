package com.lhs.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Hello world!
 *
 */
object App extends Application {
 val sparkConf = new SparkConf().setMaster("local[*]").setAppName("test")
  val sc = new SparkContext(sparkConf)
  val lines = sc.parallelize(List("this","is","test"))
  lines.collect().foreach(println)
  sc.stop()
}
