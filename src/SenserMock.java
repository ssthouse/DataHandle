import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SenserMock {

    /**
     * 获取的传感器的数据就传给它来处理
     */
    private DataHandler dataHandler;

    public SenserMock() {
        dataHandler = new DataHandler();
    }

    public void start() {
        try {
            FileReader fileReader = new FileReader("./src/data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                // 忽略掉data.txt里面的空行
                if (currentLine.length() != 0) {
                    DataFrame newDataFrame = parseOneFrameData(currentLine);

                    System.out.println("\n\n");
                    System.out.println("new Data Entered:-------------------------------");
                    System.out.println(newDataFrame.toString());
                    System.out.println("-------------------------------------------------");

                    dataHandler.newData(newDataFrame);

                    System.out.println("\n\n");
                    System.out.println("\nAfter handle new Data: ~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println(dataHandler.getTempDataFrame().toString());
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
                currentLine = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\n\nFinal Result: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        dataHandler.getHandledDataList().forEach(dataFrame -> {
            System.out.println(dataFrame.toString());
        });
    }


    private DataFrame parseOneFrameData(String dataStr) {
        //把数据处理好, 负的转为正的
        List<Integer> dataList = Arrays.stream(dataStr.split(","))
                .map(s -> {
                    int num = Integer.parseInt(s.replace(" ", ""));
                    return num;
                })
                .collect(Collectors.toList());
        return new DataFrame(dataList);
    }
}
