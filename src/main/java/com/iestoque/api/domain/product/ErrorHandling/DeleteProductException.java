package com.iestoque.api.domain.product.ErrorHandling;

public class DeleteProductException extends RuntimeException{

    public DeleteProductException(){
        super();
    }

    public DeleteProductException(String message){
        super(message);
    }

    public DeleteProductException(String message, Throwable cause){
        super(message, cause);
    }

}
