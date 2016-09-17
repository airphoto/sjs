# -*- coding:utf-8 -*-

__author__ = 'abel.li'

import urllib2
import re


class MyCrawler:
    def __init__(self, seeds):
        # 初始化当前抓取的深度
        self.current_deepth = 1
        # 使用种子初始化url队列
        self.linkQuence = linkQuence()
        #get links
        self.pattern = re.compile('<a target="_blank" href="(.*?)"')
        #get content
        self.itemsPattern = re.compile('<h1.*?mt20">(.*?)</h1>.*?<li.*?f12">(.*?)</li>.*?<li.*?li1">.*?<span.*?htm">(.*?)</a>')
        #OTC e
        self.baseAttr = re.compile('<img.*?gif"/>(.*?)</div>.*?<img.*?gif" />(.*?)</div>.*?<img.*?gif" />(.*?)</div>.*?<img.*?gif" />(.*?)</div>')
        ###########
        if isinstance(seeds, str):
            self.linkQuence.addUnvisitedUrl(seeds)
        if isinstance(seeds, list):
            for i in seeds:
                self.linkQuence.addUnvisitedUrl(i)
        print "Add the seeds url \"%s\" to the unvisited url list" % str(self.linkQuence.unVisited)

    # 抓取过程主函数
    def crawling(self, seeds, crawl_deepth):
        # 循环条件：抓取深度不超过crawl_deepth
        while self.current_deepth <= crawl_deepth:
            # 循环条件：待抓取的链接不空
            while not self.linkQuence.unVisitedUrlsEnmpy():
                # 队头url出队列
                visitUrl = self.linkQuence.unVisitedUrlDeQuence()
                print "Pop out one url \"%s\" from unvisited url list" % visitUrl
                if visitUrl is None or visitUrl == "":
                    continue
                # 获取超链接
                links = self.getHyperLinks(visitUrl)
                if self.current_deepth == 2:
                    for l in links :
                        self.getContent(l)
                print "Get %d new links" % len(links)
                # 将url放入已访问的url中
                self.linkQuence.addVisitedUrl(visitUrl)
                print "Visited url count: " + str(self.linkQuence.getVisitedUrlCount())
                print "Visited deepth: " + str(self.current_deepth)
            # 未访问的url入列
            for link in links:
                self.linkQuence.addUnvisitedUrl(link)
            print "%d unvisited links:" % len(self.linkQuence.getUnvisitedUrl())
            self.current_deepth += 1

    #获取源码中得超链接
    def getHyperLinks(self,url):
        links=[]
        data=self.getPageSource(url)
        a=re.findall(self.pattern,data)
        for i in a:
            if str(i).startswith('/'):
                links.append('http://yao.xywy.com'+str(i))
        return links

    # 获取网页源码
    def getPageSource(self, url, coding=None):
        try:
            req = urllib2.Request(url)
            req.add_header('User-agent', 'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)')
            response = urllib2.urlopen(req)
            page = response.read()
            return page
        except urllib2.URLError, e:
            print 'link error and reason is',e.reason
            return None

    def getContent(self,url):
        data = self.getPageSource(url)
        items = re.findall(self.itemsPattern,data)
        for item in items:
            base = re.findall(self.baseAttr,item[1])
            print item[0],base[0][0],base[0][1],base[0][2],base[0][3],item[2]


class linkQuence:
    def __init__(self):
        # 已访问的url集合
        self.visted = []
        # 待访问的url集合
        self.unVisited = []

    # 获取访问过的url队列
    def getVisitedUrl(self):
        return self.visted

    # 获取未访问的url队列
    def getUnvisitedUrl(self):
        return self.unVisited

    # 添加到访问过得url队列中
    def addVisitedUrl(self, url):
        self.visted.append(url)

    # 移除访问过得url
    def removeVisitedUrl(self, url):
        self.visted.remove(url)

    # 未访问过得url出队列
    def unVisitedUrlDeQuence(self):
        try:
            return self.unVisited.pop()
        except:
            return None

    # 保证每个url只被访问一次
    def addUnvisitedUrl(self, url):
        if url != "" and url not in self.visted and url not in self.unVisited:
            self.unVisited.insert(0, url)

    # 获得已访问的url数目
    def getVisitedUrlCount(self):
        return len(self.visted)

    # 获得未访问的url数目
    def getUnvistedUrlCount(self):
        return len(self.unVisited)

    # 判断未访问的url队列是否为空
    def unVisitedUrlsEnmpy(self):
        return len(self.unVisited) == 0


def main(seeds, crawl_deepth):
    craw = MyCrawler(seeds)
    craw.crawling(seeds, crawl_deepth)

if __name__ == "__main__":
    main(["http://yao.xywy.com/class.htm"], 3)
