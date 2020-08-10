package carvalho.com.KinoClub.Domain.Models.Movies;

import java.util.Date;
import java.util.UUID;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import carvalho.com.KinoClub.Domain.Models.General.IndexedEntity;

//@JsonDeserialize(builder = Movie.Builder.class)
public class Movie extends IndexedEntity {
	
	public String Title;
	public int RuntimeInSeconds;
	public String Description;
	public String Director;
	public Date ReleaseDate;
	public String Image;
	public String MovieIdentifier;
	public Movie() {
		
	}
//	public String getDirector() {
//		return Director;
//	}
//
//	public void setDirector(String director) {
//		Director = director;
//	}
//
//	public Date getReleaseDate() {
//		return ReleaseDate;
//	}
//
//	public void setReleaseDate(Date ReleaseDate) {
//		this.ReleaseDate = ReleaseDate;
//	}
//
//	public String getDescription() {
//		return this.Description;
//	}
//
//	public void setDescription(String Description) {
//		this.Description = Description;
//	}
//
//	public int getRuntimeInSeconds() {
//		return RuntimeInSeconds;
//	}
//
//	public void setRuntimeInSeconds(int runtimeInSeconds) {
//		RuntimeInSeconds = runtimeInSeconds;
//	}
//
//	public String getTitle() {
//		return Title;
//	}
//
//	public void setTitle(String title) {
//		Title = title;
//	}
//	public String getImage() {
//		return Image;
//	}
//
//	public void setImage(String image) {
//		Image = image;
//	}
//	@JsonPOJOBuilder(buildMethodName = "build", withPrefix="with")
//	public static class Builder {
//		private String Title;
//		private int RuntimeInSeconds;
//		private String Description;
//		private String Director;
//		private Date ReleaseDate;
//		private String Image;
//		private ObjectId _id;
//		public Builder withTitle(String Title) {
//			this.Title = Title;
//			return this;
//		}
//
//		public Builder withRuntimeInSeconds(int RuntimeInSeconds) {
//			this.RuntimeInSeconds = RuntimeInSeconds;
//			return this;
//		}
//
//		public Builder withDescription(String Description) {
//			this.Description = Description;
//			return this;
//		}
//
//		public Builder withDirector(String Director) {
//			this.Director=Director;
//			return this;
//		}
//
//		public Builder withReleaseDate(Date ReleaseDate) {
//			this.ReleaseDate =ReleaseDate;
//			return this;
//		}
//
//		public Builder with_id(ObjectId _id) {
//			this._id=_id;
//			return this;
//		}
//		public Builder withImage(String Image) {
//			this.Image = Image;
//			return this;
//		}
//		public Movie build() {
//			Movie movie = new Movie();
//			movie.setRuntimeInSeconds(this.RuntimeInSeconds);
//			movie.setTitle(this.Title);
//			movie.setDirector(this.Director);
//			movie.setDescription(this.Description);
//			movie.setReleaseDate(this.ReleaseDate);
//			movie.set_id(this._id);
//			movie.setImage(this.Image);
//
//			return movie;
//		}
//
//	}
	@Override
	public String toString() {
	
		return String.format("Randomly selected the movie: %s", this.Title); 
	}



}
