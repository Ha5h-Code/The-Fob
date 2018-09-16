package hashcode.thefob.utility;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.view.View;
import hashcode.thefob.R;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class PdfBuilder
{
    public static boolean buildPdf(Context context,View content)
    {
        PdfDocument document = new PdfDocument();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595,842,1).create();

        PdfDocument.Page page = document.startPage(pageInfo);



        content.draw(page.getCanvas());

        document.finishPage(page);

        String targetPdf = "Plan B";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(context, "Plan B saved Successfully at " + filePath, Toast.LENGTH_LONG).show();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        finally
        {
            // close the document
            document.close();
        }


    }
}
