package zx.soft.sdn.gather.flume.skin;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class SafePathManager  {
    private long seriesTimestamp;
    private File baseDirectory;
    private AtomicInteger fileIndex;

    private File currentFile;

    public SafePathManager() {
    }

    public File nextFile() {
        //重新生成微秒级文件名
        seriesTimestamp = System.currentTimeMillis();
        fileIndex = new AtomicInteger();
        currentFile = new File(baseDirectory, seriesTimestamp + "-"
                + fileIndex.incrementAndGet());

        return currentFile;
    }

    public File getCurrentFile() {
        if (currentFile == null) {
            return nextFile();
        }

        return currentFile;
    }

    public void rotate() {
        currentFile = null;
    }

    public File getBaseDirectory() {
        return baseDirectory;
    }

    public void setBaseDirectory(File baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public long getSeriesTimestamp() {
        return seriesTimestamp;
    }

    public AtomicInteger getFileIndex() {
        return fileIndex;
    }

}