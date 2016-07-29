package com.lhs.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by abel on 16-7-29.
  */
object TopNBasic {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("top n basic").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("ERROR")
    val df = sc.textFile("/home/abel/IdeaProjects/spark/test1/datas/topnbasic")
    val pairs = df.map(x=>(x.toInt,"")).distinct().sortByKey(false)
    pairs.map(x=>x._1).take(5).foreach(println)
    sc.stop()
  }
}
