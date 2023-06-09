package lk.earth.dbapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.HashMap;
import java.util.List;

@Service("genderService")
public class GenderService {

	@Autowired
	private GenderDao genderDao;

	public List<Gender> getAll() {
		return (List<Gender>) this.genderDao.findAll();
	}

	public Gender getById(Integer id) {
		return (Gender) this.genderDao.findAll().get(0);
	}

}
