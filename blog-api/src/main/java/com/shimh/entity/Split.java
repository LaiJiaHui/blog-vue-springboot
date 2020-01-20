package com.shimh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author linguochao
 * @Description 吐槽实体
 * @Date 2020/1/19 10:26
 */
@Entity
@Table(name = "split")
@Getter
@Setter
@ToString
public class Split {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    @Column(name = "content")
    private String content;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy.MM.dd HH:mm")
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "to_user_name")
    private String toUserName;

    @Column(name = "thumbup")
    private Integer thumbup;

    @Transient
    private List<Split> childrens;

}
