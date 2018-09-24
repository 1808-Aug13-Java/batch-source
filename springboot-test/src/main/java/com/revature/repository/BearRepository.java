package com.revature.BearRepository;
import com.revature.beans.Bear;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BearRepository extends CrudRepository<Bear, Integer> {
}
