package carvalho.com.KinoClub.Domain.Models.General;

import java.util.UUID;

public abstract class IndexedEntity {
	private UUID Identifier;

	public UUID getIdentifier() {
		return Identifier;
	}

	public void setIdentifier(UUID identifier) {
		Identifier = identifier;
	}
	public IndexedEntity() {
		setIdentifier(UUID.randomUUID());
	}
	public IndexedEntity(UUID identifier) {
		setIdentifier(identifier);
	}

}
