package org.springblade.example.poi.test.excel.read;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.handler.inter.IReadHandler;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springblade.example.poi.test.entity.MsgClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelImportUtilTest {

	///ExcelExportMsgClient 测试是这个到处的数据

	@Test
	public void test() {
		try {
			ImportParams params = new ImportParams();
			params.setTitleRows(1);
			long start = new Date().getTime();
			ExcelImportUtil.importExcelBySax(
				new FileInputStream(
					new File(FileUtilTest.getWebRootPath("import/ExcelExportMsgClient.xlsx"))),
				MsgClient.class, params, new IReadHandler<MsgClient>() {
					@Override
					public void handler(MsgClient o) {
						System.out.println(ReflectionToStringBuilder.toString(o));
					}

					@Override
					public void doAfterAll() {

					}
				});
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	public void test2() {
		ImportParams params = new ImportParams();
		params.setTitleRows(1);
		params.setHeadRows(1);
		long start = new Date().getTime();
		List<MsgClient> list = ExcelImportUtil.importExcel(
			new File(FileUtilTest.getWebRootPath("import/ExcelExportMsgClient.xlsx")),
			MsgClient.class, params);
		System.out.println(new Date().getTime() - start);
		Assert.assertEquals(100, list.size());
		System.out.println(ReflectionToStringBuilder.toString(list.get(0)));
	}

	@Test
	public void testMapImport() {
		ImportParams params = new ImportParams();
		params.setTitleRows(1);
		params.setHeadRows(1);
		long start = new Date().getTime();
		List<Map<String, Object>> list = ExcelImportUtil.importExcel(
			new File(FileUtilTest.getWebRootPath("import/ExcelExportMsgClient.xlsx")), Map.class,
			params);
		System.out.println(new Date().getTime() - start);
		Assert.assertEquals(100, list.size());
		System.out.println(list.get(0));
	}
}
