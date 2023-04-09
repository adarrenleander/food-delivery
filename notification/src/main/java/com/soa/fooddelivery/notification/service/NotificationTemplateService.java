package com.soa.fooddelivery.notification.service;

import com.soa.fooddelivery.notification.dto.NotificationTemplateDto;
import com.soa.fooddelivery.notification.entity.NotificationTemplate;
import com.soa.fooddelivery.notification.repository.NotificationTemplateRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationTemplateService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(NotificationTemplateService.class);
    @Autowired private NotificationTemplateRepository notificationTemplateRepository;

    public NotificationTemplateDto createNotificationTemplate(NotificationTemplateDto notificationTemplateDto) {
        NotificationTemplate notificationTemplate = new NotificationTemplate();
        notificationTemplate.setTitle(notificationTemplateDto.getTitle());
        notificationTemplate.setMessage(notificationTemplateDto.getMessage());
        notificationTemplate.setCategory(notificationTemplateDto.getCategory());
        notificationTemplate.setActive(true);
        notificationTemplate = notificationTemplateRepository.save(notificationTemplate);
        return notificationTemplate.convertToDto();
    }

    public NotificationTemplateDto updateNotificationTemplate(NotificationTemplateDto notificationTemplateDto) {
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findAllById(notificationTemplateDto.getNotificationTemplateId()).get(0);
        notificationTemplate.setTitle(notificationTemplateDto.getTitle());
        notificationTemplate.setMessage(notificationTemplateDto.getMessage());
        notificationTemplate.setCategory(notificationTemplateDto.getCategory());
        notificationTemplate.setActive(notificationTemplateDto.getActive());
        notificationTemplate = notificationTemplateRepository.save(notificationTemplate);
        return notificationTemplate.convertToDto();
    }

    public NotificationTemplateDto deleteNotificationTemplate(Integer notificationTemplateId) {
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findAllById(notificationTemplateId).get(0);
        notificationTemplate.setActive(false);
        notificationTemplate = notificationTemplateRepository.save(notificationTemplate);
        return notificationTemplate.convertToDto();
    }

    public ArrayList<NotificationTemplateDto> getNotificationTemplatesList() {
        ArrayList<NotificationTemplateDto> list = new ArrayList<>();
        for (NotificationTemplate notificationTemplate : notificationTemplateRepository.findAll()) {
            list.add(notificationTemplate.convertToDto());
        }
        return list;
    }

    public NotificationTemplateDto getNotificationTemplate(Integer notificationTemplateId) {
        NotificationTemplate notificationTemplate = notificationTemplateRepository.findAllById(notificationTemplateId).get(0);
        return notificationTemplate.convertToDto();
    }
}
