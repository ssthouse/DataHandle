DataFrame  ==>  定义获取的每一帧数据, 便于后续处理, 例: -74, -115, 22, 0, 3, 70, 0, 0, 1, 82, -33, 0, 3, 82, 54, 0, 4, -1, 39, -85

DataHandler  ==> 
* 用于处理传感器传来的每一帧数据, 调用 `newData(newDataFrame)`
* 数据是实时处理的, 处理完的数据放到 `DataHandler.handledDataList` 中, app展示用的应该就是 `DataHandler.handledDataList`
* SensorMock, 持有一个 `DataHandler` 的引用, 当获取到新的传感器数据时, 调用 DataHandler的 `newData(newDataFrame)` 方法

控制台中可以查看数据处理过程.