package br.ulbra.appfolhapagamento;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText edtNome, edtSalario, edtFilhos;
        Button btnCalcular;
        RadioGroup radioSexo;
        TextView txtNomeFinal, txtINSS, txtIR, txtLiquido;
        RadioButton rbFeminino, rbMasculino;

        rbMasculino = findViewById(R.id.rbMasculino);
        rbFeminino = findViewById(R.id.rbFeminino);
        edtFilhos = findViewById(R.id.edtFilhos);
        edtNome = findViewById(R.id.edtNome);
        edtSalario = findViewById(R.id.edtSalario);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtNomeFinal = findViewById(R.id.txtNomeFinal);
        radioSexo = findViewById(R.id.radioSexo);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edtNome.getText().toString();
                String salarioStr = edtSalario.getText().toString();
                String filhosStr = edtFilhos.getText().toString();


                if (salarioStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Digite um número maior que zero!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double salario = Double.parseDouble(salarioStr);
                int filhos = Integer.parseInt(filhosStr);

                double inss = 0;

                if (salario <= 1212.00) {
                    inss = salario * 0.075;
                } else if (salario <= 2427.35) {
                    inss = salario * 0.09;
                } else if (salario <= 3641.03) {
                    inss = salario * 0.12;
                } else if (salario <= 7087.22) {
                    inss = salario * 0.14;
                }

                double ir = 0;

                if (salario <= 1903.98) {
                    ir = 0;
                } else if (salario <= 2826.65) {
                    ir = salario * 0.075;
                } else if (salario <= 3751.05) {
                    ir = salario * 0.15;
                } else if (salario <= 4664.68) {
                    ir = salario * 0.225;
                }

                double salarioFamilia = 0;

                if (salario <= 1212.00) {
                    salarioFamilia = filhos * 56.47;
                }

                double salarioLiquido = salario - (inss + ir) + salarioFamilia;

                String tratamento;

                if (rbMasculino.isChecked()) {
                    tratamento = "Sr.";
                } else if (rbFeminino.isChecked()) {
                    tratamento = "Sra.";
                } else {
                    return;
                }

                String resultado =
                        tratamento + " " + nome + "\n" +
                                "INSS: R$ " + inss + "\n" +
                                "IR: R$ " + ir + "\n" +
                                "Salário Família: R$ " + salarioFamilia + "\n" +
                                "Salário Líquido: R$ " + salarioLiquido;

                txtNomeFinal.setText(resultado);
            }
        });
    }
    }