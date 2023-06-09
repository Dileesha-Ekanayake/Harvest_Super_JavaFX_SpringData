package lk.earth.dbapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.HashMap;
import java.util.List;

@Service("designationService")
public class DesignationService {

    @Autowired
    private DesignationDao designationDao;

    public List<Designation> getAll() {
        return (List<Designation>) this.designationDao.findAll();
    }

    public Designation getById(Integer id) {
        return (Designation) this.designationDao.findAll().get(0);
    }

}