package jim3xe.web.jim3xeprepwar.repository;

import jim3xe.web.jim3xeprepwar.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
}
