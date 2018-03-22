package id.ac.tazkia.auth.authserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.tazkia.auth.authserver.dto.DataResetPassword;
import id.ac.tazkia.auth.authserver.dto.NotifikasiResetPassword;
import id.ac.tazkia.auth.authserver.entity.ResetPassword;
import id.ac.tazkia.auth.authserver.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class NotifikasiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotifikasiService.class);

    @Value("${kafka.topic.notifikasi}") private String topicNotifikasi;
    @Value("${notifikasi.registrasi.konfigurasi.it-reset-password}") private String getKonfigurasiNotifikasiResetPassword;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;




    @Async
    public void resetPassword(ResetPassword p){
        User user = p.getUser();
        NotifikasiResetPassword notif = NotifikasiResetPassword.builder().build().builder().build().builder()
                .konfigurasi(getKonfigurasiNotifikasiResetPassword)
                .email(user.getEmail())
                .data(DataResetPassword.builder()
                        .code(p.getCode())
                        .nama(p.getUser().getUsername())
                        .build())
                .build();

        try {
            kafkaTemplate.send(topicNotifikasi, objectMapper.writeValueAsString(notif));
        } catch (Exception err) {
            LOGGER.warn(err.getMessage(), err);
        }
    }
}
