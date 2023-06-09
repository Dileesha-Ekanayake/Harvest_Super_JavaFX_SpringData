package lk.earth.dbapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("statusemployeeService")
public class StatusemployeeService {

    @Autowired
    private StatusemployeeDao statusemployeeDao;

    public List<Statusemployee> getAll() {
        return (List<Statusemployee>) this.statusemployeeDao.findAll();
    }

    public Statusemployee getById(Integer id) {
        return (Statusemployee) this.statusemployeeDao.findAll().get(0);
    }

}