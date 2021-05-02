package com.hotel.util;

import com.hotel.entities.Hotel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;


public class HotelSpecificationBuilder {
	 private final List<SpecSearchCriteria> params;

	    public HotelSpecificationBuilder() {
	        params = new ArrayList<>();
	    }

	    // API

	    public final HotelSpecificationBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix,String entityType) {
	        return with(null, key, operation, value, prefix, suffix,entityType);
	    }

	    public final HotelSpecificationBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix,String entityType) {
	        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
	        if (op != null) {
	            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
	                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
	                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

	                if (startWithAsterisk && endWithAsterisk) {
	                    op = SearchOperation.CONTAINS;
	                } else if (startWithAsterisk) {
	                    op = SearchOperation.ENDS_WITH;
	                } else if (endWithAsterisk) {
	                    op = SearchOperation.STARTS_WITH;
	                }
	            }
	            params.add(new SpecSearchCriteria(orPredicate, key, op, value,entityType));
	        }
	        return this;
	    }

	    public Specification<Hotel> build() {
	        if (params.size() == 0)
	            return null;

	        Specification<Hotel> result = new HotelSpecification(params.get(0));
	     
	        for (int i = 1; i < params.size(); i++) {
	            result = params.get(i).isOrPredicate()
	              ? Specification.where(result).or(new HotelSpecification(params.get(i))) 
	              : Specification.where(result).and(new HotelSpecification(params.get(i)));
	        }
	        
	        return result;
	    }

	    public final HotelSpecificationBuilder with(HotelSpecification spec) {
	        params.add(spec.getCriteria());
	        return this;
	    }

	    public final HotelSpecificationBuilder with(SpecSearchCriteria criteria) {
	        params.add(criteria);
	        return this;
	    }
/*  private final List<SearchCriteria> params;
  
  public HotelSpecificationBuilder() {
    params = new ArrayList<>();
  }
	  public Specification<Hotel> build() { 
	    // convert each of SearchCriteria params to Specification and construct combined specification based on custom rules
		     if (params.size() == 0) {
		            return null;
		        }

		        List<Specification> specs = params.stream()
		          .map(UserSpecification::new)
		          .collect(Collectors.toList());
		        
		        Specification result = specs.get(0);

		        for (int i = 1; i < params.size(); i++) {
		            result = params.get(i)
		              .isOrPredicate()
		                ? Specification.where(result)
		                  .or(specs.get(i))
		                : Specification.where(result)
		                  .and(specs.get(i));
		        }       
		        return result;
	  }
	  public final HotelSpecificationBuilder with(final SearchCriteria criteria) { 
	    params.add(criteria);
	    return this;
	  }*/
}
