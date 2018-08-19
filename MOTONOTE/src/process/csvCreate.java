package process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import dto.SelectDTO;

public class csvCreate {

    public static void exportCsv(int month,int sum,int income,int spending,ArrayList<SelectDTO> list){

        try {

            // 出力ファイルの作成
        	File file = new File("C:\\Users\\高橋秀英\\Downloads\\家計簿.csv");
            PrintWriter p = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"Shift-JIS")));

            // ヘッダーを指定する
            p.print(month+"月収支："+sum+",");
            p.print(month+"月収入："+income+",");
            p.print(month+"月支出："+spending);
            p.println();
            p.print("収支,内容,金額,日付");
            p.println();

            // 内容をセットする
            for(int i = 0; i < list.size(); i++){
            	String re = (0==list.get(i).getRe())?"収入":"支出";
                p.print(re);
                p.print(",");
                p.print(list.get(i).getContent());
                p.print(",");
                p.print(list.get(i).getCost());
                p.print(",");
                p.print(list.get(i).getCalender());
                p.println();    // 改行
            }

            // ファイルに書き出し閉じる
            p.close();

            System.out.println("ファイル出力完了！");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}