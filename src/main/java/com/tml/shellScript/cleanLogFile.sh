#!/bin/bash
#定时清理服务器上的日志文件
#使用logback日志系统，会在服务器上生成.tmp的日志文件，还有按照日期生成.zip压缩文件，这些文件白白浪费了服务器上的硬件资源，
#现通过定时任务每天清理10天之前的日志文件

#清理10天之前的.tmp日志文件
find /log/ -type f -name "*.tmp" -mtime +10 -exec rm {} \;

#清理10天之前的.zip日志文件
find /log/ -type f -name "*.zip" -mtime +10 -exec rm {} \;