package br.com.caelum.cadastro.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDao;

/**
 * Created by android5044 on 30/05/15.
 */
public class SMSReceiver extends BroadcastReceiver {

    AlunoDao alunoDao;

    @Override
    public void onReceive(Context context, Intent intent) {

        alunoDao=new AlunoDao(context);

        Bundle bundle = intent.getExtras();
        Object[] mensagens = (Object[]) bundle.get("pdus");
        byte[] mensagem = (byte[]) mensagens[0];
        SmsMessage sms = SmsMessage.createFromPdu(mensagem);

        if(alunoDao.isAluno(sms.getDisplayOriginatingAddress())) {
            Toast.makeText(context, "chegou um SMS do aluno: " + sms.getDisplayOriginatingAddress(), Toast.LENGTH_LONG).show();
            MediaPlayer mp = MediaPlayer.create(context, R.raw.cricket);
            mp.start();
        }
    }
}
