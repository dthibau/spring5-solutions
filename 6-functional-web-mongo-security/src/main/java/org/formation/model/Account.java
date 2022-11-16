package org.formation.model;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Account {
	
	@Id
    private String id;
    private String owner;
    private Double amount;
    
    public Account(String owner, Double amount) {
        this.owner = owner;
        this.amount = amount;
    }
    
	@Override
	public String toString() {
		return "Account [id=" + id + ", owner=" + owner + ", amount=" + amount + "]";
	}
    
    

}
