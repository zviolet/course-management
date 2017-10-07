import com.zviolet.dao.TeacherDao;
import com.zviolet.entity.PagingVO;
import com.zviolet.entity.Teacher;
import com.zviolet.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherDao teacherDao;

    public List<Teacher> findAll() {
        return teacherDao.selectAll();
    }

    public List<Teacher> findByPaging(Integer page) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setShowPage(page);
        pagingVO.setShowRecordNo((page - 1) * pagingVO.getPageSize());
        List<Teacher> teacherList = teacherDao.findByPaging(pagingVO);
        return teacherList;
    }

    public boolean save(Teacher teacher) throws Exception {
        Teacher tea = teacherDao.selectById(teacher.getTeacherId());
        if (tea == null) {
            teacherDao.insert(teacher);
            return true;
        }
        return false;
    }
    
    public Teacher findById(Integer id) throws Exception {
        return teacherDao.selectById(id);
    }

    public void updateById(Teacher teacher) throws Exception {
        teacherDao.updateById(teacher);
    }
}
