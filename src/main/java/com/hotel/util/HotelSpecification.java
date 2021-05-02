package com.hotel.util;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hotel.entities.Hotel;
import com.hotel.entities.Review;
import com.hotel.entities.User;

public class HotelSpecification implements Specification<Hotel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SpecSearchCriteria criteria;

	public HotelSpecification(final SpecSearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public SpecSearchCriteria getCriteria() {
		return criteria;
	}

	@Override
	public Predicate toPredicate(final Root<Hotel> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
		switch (criteria
				.getOperation()) {/*
									 * Join<Pet, Owner> owners =
									 * root.join("owners");
									 * criteriaQuery.orderBy(criteriaBuilder.
									 * desc(root.get("id"))); return
									 * criteriaBuilder.equal(owners.get("name"),
									 * ownerName);
									 */
		/*
		 * case EQUALITY: return builder.equal(root.get(criteria.getKey()),
		 * criteria.getValue());
		 */
		case EQUALITY: {
			/*
			 * List cats = sess.createCriteria(Cat.class) .add(
			 * Restrictions.like("name", "F%") .addOrder( Order.asc("name") )
			 * .addOrder( Order.desc("age") ) .setMaxResults(50) .list();
			 */
			// Join<Pet, Owner> owners = root.join("owners");

			/*
			 * query.orderBy(builder.desc(root.get("averageRating"))); return
			 * builder.equal(root.get(criteria.getKey()), criteria.getValue());
			 * 
			 */
			// root.get(root.get(""));
			if (criteria.getEntityType().equals("Facility")) {
				;
				/*
				 * criteriaBuilder.equal(customer.get(Customer_.shipmentInfo)
				 * .get(ShipmentInfo_.address) .get(Address_.state), "NJ")
				 */

				// return builder.equal(root.get("facility."+criteria.getKey()),
				// criteria.getValue());
				return builder.equal(root.get("facility").get(criteria.getKey()), criteria.getValue());

			}
			if (criteria.getEntityType().equals("Rating")) {
				;
				/*
				 * criteriaBuilder.equal(customer.get(Customer_.shipmentInfo)
				 * .get(ShipmentInfo_.address) .get(Address_.state), "NJ")
				 */

				// return builder.equal(root.get("facility."+criteria.getKey()),
				// criteria.getValue());
				
				
				System.out.println("inside rating key"+criteria.getKey()+" value"+ criteria.getValue());
		/*	    Join<Hotel,Review> userProd = root.join("reviews");
	            Join<Review,User> prodRelation = userProd.join("user");
	            return builder.equal(prodRelation.get(criteria.getKey()), criteria.getValue());*/
				Join<Object, Object> joinParent = root.join("reviews");
				Path expression = joinParent.get("user");
				System.out.println("gender after"+expression.get("gender"));
			 return builder.equal(expression.get(criteria.getKey()), criteria.getValue());
	            
	            
				//return builder.equal(root.get("reviews").get("user").get(criteria.getKey()), criteria.getValue());
	           // return builder.equal(prodRelation.get("follower"), input);
			}
			
			else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
			// return builder.equal(root.get(criteria.getKey()),
			// criteria.getValue());
		}

		case NEGATION:
			if (criteria.getEntityType().equals("Facility")) {

				// return builder.equal(root.get("facility."+criteria.getKey()),
				// criteria.getValue());
				return builder.notEqual(root.get("facility").get(criteria.getKey()), criteria.getValue());

			} else {
				return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
			}

		case GREATER_THAN:
			if (criteria.getEntityType().equals("Facility")) {

				// return builder.equal(root.get("facility."+criteria.getKey()),
				// criteria.getValue());
				return builder.greaterThan(root.get("facility").get(criteria.getKey()), criteria.getValue().toString());

			} else {
				return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
			}

		case LESS_THAN:
			if (criteria.getEntityType().equals("Facility")) {

				// return builder.equal(root.get("facility."+criteria.getKey()),
				// criteria.getValue());
				return builder.lessThan(root.get("facility").get(criteria.getKey()), criteria.getValue().toString());

			} else {
				return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
			}

		case LIKE:
			if (criteria.getEntityType().equals("Facility")) {

				// return builder.equal(root.get("facility."+criteria.getKey()),
				// criteria.getValue());
				return builder.like(root.get("facility").get(criteria.getKey()), criteria.getValue().toString());

			} else {
				return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
			}
			// return builder.like(root.get(criteria.getKey()),
			// criteria.getValue().toString());
		case STARTS_WITH:
			if (criteria.getEntityType().equals("Facility")) {

				// return builder.equal(root.get("facility."+criteria.getKey()),
				// criteria.getValue());
				return builder.like(root.get("facility").get(criteria.getKey()), criteria.getValue() + "%");

			} else {
				return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
			}
			// return builder.like(root.get(criteria.getKey()),
			// criteria.getValue() + "%");
		case ENDS_WITH:
			if (criteria.getEntityType().equals("Facility")) {

				// return builder.equal(root.get("facility."+criteria.getKey()),
				// criteria.getValue());
				return builder.like(root.get("facility").get(criteria.getKey()), "%" + criteria.getValue());

			} else {
				return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
			}
			// return builder.like(root.get(criteria.getKey()), "%" +
			// criteria.getValue());
		case CONTAINS:
			if (criteria.getEntityType().equals("Facility")) {

				// return builder.equal(root.get("facility."+criteria.getKey()),
				// criteria.getValue());
				return builder.like(root.get("facility").get(criteria.getKey()), "%" + "%" + criteria.getValue() + "%");

			} else {
				return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
			}
			// return builder.like(root.get(criteria.getKey()), "%" +
			// criteria.getValue() + "%");
		default:
			return null;
		}
	}

}
