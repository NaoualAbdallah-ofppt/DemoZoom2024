package aa.bb.demozoom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
ImageView img;
    ViewGroup.LayoutParams P = null;
    final int  X_Initiale=400;
    final  int Y_Initiale=400;
    double distanceDepart=0, distanceFin=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.img);
      P= img.getLayoutParams();
        P.width=X_Initiale;
        P.height=Y_Initiale;
        img.setLayoutParams(P);
img.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        double distanceX, distanceY;
        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK){

            case MotionEvent.ACTION_POINTER_DOWN:
                if (motionEvent.getPointerCount()>1)
                {
                distanceX = motionEvent.getX(0) - motionEvent.getX(1);
                distanceY = motionEvent.getY(0) - motionEvent.getY(1);
                distanceDepart = Math.sqrt(
                        distanceX * distanceX + distanceY * distanceY);}
                break;
            case MotionEvent.ACTION_MOVE:
                if (motionEvent.getPointerCount()>1) {
                    distanceX = motionEvent.getX(0) - motionEvent.getX(1);
                    distanceY = motionEvent.getY(0) - motionEvent.getY(1);
                    distanceFin = Math.sqrt(
                            distanceX * distanceX + distanceY * distanceY);
                    redimensionner();
                }
                break;
        }


        return true;
    }
});
    }
    private void redimensionner(){
        //Formule pour modifier la taille de l’image en fonction
        // d’une échelle
        double Echelle = distanceFin/distanceDepart;
        if (Echelle < 0.1) Echelle = 0.1;
        P.width=(int) (X_Initiale * Echelle);
        P.height=(int) (Y_Initiale * Echelle);
        img.setLayoutParams(P);
    }

}