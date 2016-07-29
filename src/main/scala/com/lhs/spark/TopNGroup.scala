package com.lhs.spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by abel on 16-7-29.
  *
  *
  * 分组的 topN 算法    sortBy与sorted 都默认按照升序的方法排序，下文都是使用降序排列的
  */
object TopNGroup {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("top n basic").setMaster("local[*]")
    val sc = new SparkContext(sparkConf)
    sc.setLogLevel("ERROR")
    val df = sc.textFile("/home/abel/IdeaProjects/spark/test1/datas/topNGroup")
    val pairs = df.mapPartitions(_.map(x=>{
      val fields = x.split(" ")
      (fields(0),fields(1).toInt)
    })).distinct()

    val result = pairs.groupByKey.map(x=>{x._1+"->"+x._2.toSeq.sortBy(y => -y).take(5)})
//    val result = pairs.groupByKey.map(x=>{x._1+"->"+x._2.toSeq.sortWith(_>_).take(5)})

    result.collect().foreach(println)
    sc.stop()
  }
}
