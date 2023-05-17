package com.hyxc.moikiitos.domain;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Post {
	
	private static final int MAX_MESSAGE_SIZE = 500;
	
	private static final PeriodFormatter PERIOD_FORMATTER = new PeriodFormatterBuilder()
	    .appendDays()
	    .appendSuffix("d")
	    .appendSeparator(" ")
	    .appendHours()
	    .appendSuffix("h")
	    .appendSeparator(" ")
	    .printZeroAlways()
	    .appendMinutes()
	    .appendSuffix("m")
	    .toFormatter();
	
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "username")
	private BlogUser blogUser;
	
	@Size(min=1, max = MAX_MESSAGE_SIZE, message="Post {javax.validation.constraints.Size.message}")
	@Column(nullable = false, length = MAX_MESSAGE_SIZE)
	private String message;
	
	@Column(nullable = false)
	private Date createdDate;
	
	@Transient
	private Date retrievalDate = new Date();
	
	protected static String getAge(final Date fromDate, final Date createdDate) {
		DateTime now = new DateTime(fromDate);
		DateTime created = new DateTime(createdDate);
		
		Period period = new Period(created, now);
		String age = "";
		
		int weeksAgo = period.getWeeks();
		if(weeksAgo == 0) {
			age = PERIOD_FORMATTER.print(period.normalizedStandard());
		} else {
			age = "a long while ago";
		}
		
		return age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public BlogUser getBlogUser() {
		return blogUser;
	}

	public void setBlogUser(BlogUser blogUser) {
		this.blogUser = blogUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@DateTimeFormat(style="MM")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * How long ago was this post made? This is calculated based on the post's
	 * created date, and is not saved to the database.
	 */
	@Transient
	public String getAge() {
		return Post.getAge(new Date(), this.createdDate);
	}
	
	@Transient
	public Date getRetrievalDate() {
		return retrievalDate;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
