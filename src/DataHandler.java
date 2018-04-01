import java.util.ArrayList;
import java.util.List;

public class DataHandler {

    private List<DataFrame> handledDataList;

    // 当前正在处理的dataFrame
    private DataFrame tempDataFrame;

    public DataHandler() {
        this.handledDataList = new ArrayList<>();
    }


    /**
     * 处理新数据
     *
     * @param dataFrame
     */
    public void newData(DataFrame dataFrame) {
        // 特别处理第一帧数据
        if (tempDataFrame == null) {
            this.tempDataFrame = dataFrame;
            return;
        }
        // 当接收到的新数据和tempDataFrame还是同一组数据时 ==>  将新数据和tempDataFrame叠加
        if (tempDataFrame.getTimestamp().equals(dataFrame.getTimestamp())) {
            updateTempDataWith(dataFrame);
        } else {
            // 如果换下一组了, 将当前处理好的数据存入handledDataList ==> tempDataFrame切换到当前最新的dataFrame
            this.handledDataList.add(tempDataFrame);
            this.tempDataFrame = dataFrame;
        }
    }

    private void updateTempDataWith(DataFrame dataFrame) {
        if (dataFrame.getSensor1Adc() > tempDataFrame.getSensor1Adc()) {
            tempDataFrame.setSensor1Time(dataFrame.getSensor1Time());
            tempDataFrame.setSensor1Adc(dataFrame.getSensor1Adc());
        }
        if (dataFrame.getSensor2Adc() > tempDataFrame.getSensor2Adc()) {
            tempDataFrame.setSensor2Time(dataFrame.getSensor2Time());
            tempDataFrame.setSensor2Adc(dataFrame.getSensor2Adc());
        }
        if (dataFrame.getSensor3Adc() > tempDataFrame.getSensor3Adc()) {
            tempDataFrame.setSensor3Time(dataFrame.getSensor3Time());
            tempDataFrame.setSensor3Adc(dataFrame.getSensor3Adc());
        }
        // TODO: 着地时间 和 腾空时间会有多个吗?
        if (dataFrame.getGroundTime() != -1) {
            tempDataFrame.setGroundTime(dataFrame.getGroundTime());
        }
        if (dataFrame.getUpGroundTime() != -1) {
            tempDataFrame.setUpGroundTime(dataFrame.getUpGroundTime());
        }
    }

    public DataFrame getTempDataFrame() {
        return tempDataFrame;
    }

    public void setTempDataFrame(DataFrame tempDataFrame) {
        this.tempDataFrame = tempDataFrame;
    }

    public List<DataFrame> getHandledDataList() {
        return handledDataList;
    }

    public void setHandledDataList(List<DataFrame> handledDataList) {
        this.handledDataList = handledDataList;
    }
}
