package mybatis.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import mybatis.vo.MemVO;

public class MemDAO {

	@Autowired
	private SqlSessionTemplate sst;
	
	public MemDAO(){  //생성자
		
		System.out.println("MemDAO!");
	}
	
	//회원목록
	public MemVO[] getAll() {
		
		MemVO[] ar = null;
		
		List<MemVO> list = sst.selectList("mem.all");
		
		if(list != null && list.size() > 0) {
			ar = new MemVO[list.size()];  //MemVO를 저장할 수 있는 공간을 마련했습니다.
			
			list.toArray(ar);
		}
		
		return ar;
	}
	
}
