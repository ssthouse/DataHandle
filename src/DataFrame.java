import java.util.List;

public class DataFrame {

    // 把 1~4 个数字拼接起来作为时间戳
    private String timestamp;

    // 用 -1 代表当前数据帧中, 该数据没有被采集到
    private int sensor1Time = -1;
    private int sensor1Adc = -1;

    private int sensor2Time = -1;
    private int sensor2Adc = -1;

    private int sensor3Time = -1;
    private int sensor3Adc = -1;

    private int groundTime = -1;
    private int upGroundTime = -1;

    /**
     * 数据中的负数已经转换好了
     *
     * @param dataList
     */
    public DataFrame(List<Integer> dataList) {
        this.timestamp = "" + dataList.get(0) + dataList.get(1) + dataList.get(2) + dataList.get(3);

        // 除了前面的timestamp ==>  后面的负数都 & 0xff
        for(int i = 4; i< dataList.size(); i++) {
            if(dataList.get(i) < 0){
                dataList.set(i, dataList.get(i) & 0xff);
            }
        }

        for (int dataGroupIndex = 1; dataGroupIndex <= 4; dataGroupIndex++) {
            int sensorMarkIndex = dataGroupIndex * 4 + 0;
            switch (dataList.get(sensorMarkIndex)) {
                case 1:
                    this.sensor1Time = dataList.get(sensorMarkIndex + 1);
                    this.sensor1Adc = dataList.get(sensorMarkIndex + 2) + dataList.get(sensorMarkIndex + 3) * 256;
                    break;
                case 2:
                    this.sensor2Time = dataList.get(sensorMarkIndex + 1);
                    this.sensor2Adc = dataList.get(sensorMarkIndex + 2) + dataList.get(sensorMarkIndex + 3) * 256;
                    break;
                case 3:
                    this.sensor3Time = dataList.get(sensorMarkIndex + 1);
                    this.sensor3Adc = dataList.get(sensorMarkIndex + 2) + dataList.get(sensorMarkIndex + 3) * 256;
                    break;
                case 4:
                    this.groundTime = dataList.get(sensorMarkIndex + 1);
                    this.upGroundTime = dataList.get(sensorMarkIndex + 2) + dataList.get(sensorMarkIndex + 3) * 256;
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "\ntimestamp:     " + this.timestamp
                + "\nsensor1: " + this.sensor1Time + "ms     adc: " + this.sensor1Adc
                + "\nsensor2: " + this.sensor2Time + "ms     adc: " + this.sensor2Adc
                + "\nsensor3: " + this.sensor3Time + "ms     adc: " + this.sensor3Adc
                + "\nground time: " + this.groundTime + "   upGroundTime: " + this.upGroundTime;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getSensor1Time() {
        return sensor1Time;
    }

    public int getSensor1Adc() {
        return sensor1Adc;
    }

    public int getSensor2Time() {
        return sensor2Time;
    }

    public int getSensor2Adc() {
        return sensor2Adc;
    }

    public int getSensor3Time() {
        return sensor3Time;
    }

    public int getSensor3Adc() {
        return sensor3Adc;
    }

    public int getGroundTime() {
        return groundTime;
    }

    public int getUpGroundTime() {
        return upGroundTime;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSensor1Time(int sensor1Time) {
        this.sensor1Time = sensor1Time;
    }

    public void setSensor1Adc(int sensor1Adc) {
        this.sensor1Adc = sensor1Adc;
    }

    public void setSensor2Time(int sensor2Time) {
        this.sensor2Time = sensor2Time;
    }

    public void setSensor2Adc(int sensor2Adc) {
        this.sensor2Adc = sensor2Adc;
    }

    public void setSensor3Time(int sensor3Time) {
        this.sensor3Time = sensor3Time;
    }

    public void setSensor3Adc(int sensor3Adc) {
        this.sensor3Adc = sensor3Adc;
    }

    public void setGroundTime(int groundTime) {
        this.groundTime = groundTime;
    }

    public void setUpGroundTime(int upGroundTime) {
        this.upGroundTime = upGroundTime;
    }
}
