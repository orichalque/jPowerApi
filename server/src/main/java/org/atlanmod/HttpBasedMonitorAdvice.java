package org.atlanmod;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import net.bytebuddy.asm.Advice;

public class HttpBasedMonitorAdvice {

    @Advice.OnMethodEnter
    static void enter() throws UnirestException {
        Unirest.post("http://localhost:7070/start?pid="+SystemUtils.getPID())
                .header("accept", "application/json")
                .asJson();
    }

    @Advice.OnMethodExit
    static void exit() throws UnirestException {
        Unirest.post("http://localhost:7070/stop")
                .header("accept", "application/json")
                .asJson();
    }
}
