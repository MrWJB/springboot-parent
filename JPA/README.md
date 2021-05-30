spring data jpa中，只要方法的定义符合既定规范，spring data就能分析出开发者的意图，
从而避免开发者定义SQL。
所谓既定规范，就是一定的方法命名规则。
支持的命名规则如表：
1. And
2. Or
3. Is
4. Equals
5. Between
6. LessThan
7. LessThanEquals
8. GreaterThan
9. GreaterThanEquals
10. After
11. Before
12. IsNull
13. IsNotNull,NotNull
14. Not
15. In
16. NotIn
17. NotLike
18. Like
19. StartingWith
20. EndingWith
21. Containing,Contains
22. OrderBy 
23. True
24. False
25. IgnoreCase

既定的方法命名规则不一定满足所有的开发需求，因此spring data jpa 也支持自定义JPQL或者原生的
SQL。