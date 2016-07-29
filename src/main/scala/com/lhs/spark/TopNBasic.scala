package com.lhs.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by abel on 16-7-29.
  *
  * topN 基础算法的实现，用到了 sortByKey 算子， 该算子默认的是升序的，加入参数false之后就变成了降序
  * sortByKey 使用的是  rangePartitioner  使用了水塘抽样的算法。
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
