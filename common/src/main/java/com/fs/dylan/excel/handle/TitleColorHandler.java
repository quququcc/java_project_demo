package com.fs.dylan.excel.handle;

import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.context.CellWriteHandlerContext;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

import java.util.Map;

/**
 * @ClassName TitleColorHandler
 * @Description 表头颜色处理
 * @Author Alex.Zhang
 * @Date 2023/6/15
 * @Version 1.0
 */
public class TitleColorHandler implements CellWriteHandler {

    private final Map<Integer, Short> colorMap;

    public TitleColorHandler(Map<Integer, Short> colorMap) {
        this.colorMap = colorMap;
    }

    @Override
    public void afterCellDispose(CellWriteHandlerContext context) {
        Integer columnIndex = context.getColumnIndex();
        if (Boolean.FALSE.equals(context.getHead()) || !colorMap.containsKey(columnIndex)) {
            return;
        }
        WriteCellData<?> cellData = context.getFirstCellData();
        WriteCellStyle writeCellStyle = cellData.getOrCreateStyle();
        writeCellStyle.setFillForegroundColor(colorMap.get(columnIndex));
        writeCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
    }
}
