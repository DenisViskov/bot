package my_diet_diary_bot.bot.domain;

import my_diet_diary_bot.bot.service.Actions;
import my_diet_diary_bot.bot.service.ModeTypes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 21.12.2020
 */
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "chatId")
    private long chatId;
    @Column(name = "userName")
    private String userName;
    @Enumerated(EnumType.STRING)
    @Column(name = "modeType")
    private ModeTypes modeType;
    @Enumerated(EnumType.STRING)
    @Column(name = "last_action")
    private Actions last_action;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;

    public Person() {
        products = new HashSet<>();
    }

    public Person(long chatId, String userName, ModeTypes modeType, Actions last_action) {
        this.chatId = chatId;
        this.userName = userName;
        this.modeType = modeType;
        this.last_action = last_action;
        products = new HashSet<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ModeTypes getModeType() {
        return modeType;
    }

    public void setModeType(ModeTypes modeType) {
        this.modeType = modeType;
    }

    public Actions getLast_action() {
        return last_action;
    }

    public void setLast_action(Actions last_action) {
        this.last_action = last_action;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                chatId == person.chatId &&
                Objects.equals(userName, person.userName) &&
                modeType == person.modeType &&
                last_action == person.last_action &&
                Objects.equals(products, person.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, userName, modeType, last_action, products);
    }
}
