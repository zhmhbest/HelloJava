package org.example.config;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.example.IO;
import org.example.config.entities.Person;


class DataListener extends AnalysisEventListener<Person> {
    @Override
    public void invoke(Person person, AnalysisContext context) {
        System.out.println(person);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("解析完成！");
    }
}


public class HelloExcel {
    public static void main(String[] args) {
        EasyExcel.read(
                IO.getResourceStream("/demo.xlsx"),
                Person.class,
                new DataListener()
        ).sheet("工作表1").doRead();
    }
}
