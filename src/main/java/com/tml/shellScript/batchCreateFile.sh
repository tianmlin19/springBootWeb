#!/bin/bash
# 使用循环创建文件
for((i=1;i<100;i++))
do
    if((i%3==0))
    then
        echo $i
        continue
    fi
done