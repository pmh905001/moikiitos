/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hyxc.moikiitos.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class BlogAuthority implements Serializable {

	private static final long serialVersionUID = 3156968893620763630L;
	
	@Id
	private AuthorityKey authorityKey;

	public AuthorityKey getAuthorityKey() {
		return authorityKey;
	}

	public void setAuthorityKey(AuthorityKey authorityKey) {
		this.authorityKey = authorityKey;
	}
	
}
