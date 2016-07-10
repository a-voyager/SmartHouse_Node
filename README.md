# 智能家居系统(感知端)
感知端(节点端)由树莓派和硬件组成:
 - 硬件负责初始化传感器和采集传感器数据，同时响应串口中断，回应数据；
 - 树莓派作为一个中间件，采用java和c混编，一面采用Socket与服务器交换数据，另一面通过433Mhz无线模块与硬件进行实时通信

# 图片展示
# ![image](https://github.com/a-voyager/SmartHouse_Node/raw/master/imgs/img (1).jpg "效果图")

# License
    The MIT License (MIT)

    Copyright (c) 2015 WuHaojie(吴豪杰)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.