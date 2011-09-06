package org.broad.igv.hic;

import org.broad.igv.hic.data.*;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * @author jrobinso
 * @date Aug 2, 2010
 */
public class ThumbnailPanel extends JComponent implements Serializable {

    private MainWindow mainWindow;
    private MatrixZoomData zd;
    private int maxCount = 50;
    private int binWidth = 1;
    private Context context;

    HeatmapRenderer renderer = new HeatmapRenderer();


    public ThumbnailPanel() {

    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public String getName() {
        return null;
    }

    public void setName(String nm) {

    }


    @Override
    protected void paintComponent(Graphics g) {

        if (getZd() != null) {
            Rectangle bounds = this.getVisibleRect();

            renderer.render(0, 0, getZd(), getBinWidth(), getMaxCount(), g, bounds);
            renderVisibleWindow(g);
        }
    }

    private void renderVisibleWindow(Graphics g) {
        if (mainWindow != null && mainWindow.context.getVisibleWidth() > 0) {

            int bw = getBinWidth();
            int nBins = mainWindow.len / zd.getBinSize();
            int effectiveWidth = nBins * bw;

            int w = (int) ((((double) mainWindow.context.getVisibleWidth()) / mainWindow.len) * effectiveWidth);
            int h = (int) ((((double) mainWindow.context.getVisibleHeight()) / mainWindow.len) * effectiveWidth);
            int x = (int) ((((double) getContext().getOriginX()) / mainWindow.len) * effectiveWidth);
            int y = (int) ((((double) getContext().getOriginY()) / mainWindow.len) * effectiveWidth);
            g.setColor(Color.black);
            g.drawRect(x, y, w, h);
        }
    }

    public MatrixZoomData getZd() {
        return zd;
    }

    public void setZd(MatrixZoomData zd) {
        this.zd = zd;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public int getBinWidth() {
        return binWidth;
    }

    public void setBinWidth(int binWidth) {
        this.binWidth = binWidth;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
