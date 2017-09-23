package factory.software.our.barcodereader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeReaderActivity extends AppCompatActivity
{
    private ZXingScannerView scannerView;

     @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_reader);
    }

    public void Scan(View view)
    {
        scannerView = new ZXingScannerView(this);
        scannerView.setResultHandler(new ZXingScannerResultHandler());

        setContentView(scannerView);
        scannerView.startCamera();
    }

    @Override
    public void onPause()
    {
        super.onPause();
        scannerView.stopCamera();
    }

    class ZXingScannerResultHandler implements ZXingScannerView.ResultHandler
    {
        @Override
        public void handleResult(Result result)
        {
            String resultCode = result.getText();
            Toast.makeText(BarcodeReaderActivity.this, resultCode, Toast.LENGTH_SHORT).show();

            setContentView(R.layout.activity_barcode_reader);
            scannerView.stopCamera();
        }
    }
}
