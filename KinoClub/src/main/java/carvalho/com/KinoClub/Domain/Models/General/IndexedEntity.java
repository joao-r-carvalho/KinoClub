package carvalho.com.KinoClub.Domain.Models.General;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
public abstract class IndexedEntity {
	
	@Id
	protected ObjectId _id;
	
	public ObjectId get_id() {
		return _id;
	}
	

	public void set_id(ObjectId identifier) {
		_id = identifier;
	}
	public IndexedEntity() {
		set_id(new ObjectId());
	}
	public IndexedEntity(ObjectId identifier) {
		set_id(identifier);
	}

}
