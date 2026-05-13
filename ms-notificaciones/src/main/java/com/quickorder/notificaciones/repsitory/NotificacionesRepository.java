package com.quickorder.notificaciones.repsitory;


import com.quickorder.notificaciones.model.NotificacionesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionesRepository extends JpaRepository<NotificacionesModel, Long> {

}
