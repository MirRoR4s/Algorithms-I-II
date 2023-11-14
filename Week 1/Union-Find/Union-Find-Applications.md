# 并查应用

好吧。 Alright.  

现在我们已经看到了算法的有效实现，可以解决大型问题实例的统一问题，让我们看看如何应用它。  
Now that we've seen efficient implementations of algorithms that can solve the unifying problem for huge problem instances let's look to see how that might be applied.  

Union-find 有大量的应用。  
There's a huge number of applications of Union-find.  

我们讨论了网络中的动态连接，在我们的计算基础设施中还有很多其他的例子。  We talked about dynamic connectivity in networks, there's many other examples in our computational infrastructure.  

Down at the bottom 是其中重要的一个——理解如何在图像处理中标记图像中的区域。  Down at the bottom is one of those important one is in image processing for understanding how to label areas in images.  

我们稍后会看到 Kruskal 的最小生成树算法，这是一种图形处理算法，它使用 Union-find 作为子程序。  
We'll see later Kruskal's minimum spanning tree algorithm, which is a graph processing algorithm which uses Union-find as a subroutine.  

物理学中有一些算法可以用来理解物理现象，我们会看一个例子以及这个列表中的许多其他例子。  There's algorithms in physics for understanding physical phenomenon that we'll look at an example and many others on this list.  

我们现在要讲的是渗透作用。  So, the one we're going to talk about now is called percolation.  

这是很多物理系统的模型，我先给出一个抽象的模型然后简单讲一下它是如何应用于物理系统的。  That's a model for many physical systems I'll give an abstract model and then just talk briefly about how it applies to physical systems.  

我们考虑一个n × n的正方形网格，我们称之为站点。  So let's think of an n by n grid of squares that we call sites.  

假设每个站点都是开放的。  And we'll say that each site is open.

图中白色部分的概率为 P，黑色的部分概率为 1 - P，然后我们定义一个系统，如果顶部和底部通过开放的站点相连起来，我们就说一个系统是渗透的。  That's white in the diagram with probably P or blocked, that's black of the diagram with probability one - P and we define a system to, we say that a system is percolated if the top and the bottom are connected by open sites.  

所以左边的系统，你可以通过白色方块找到从上到下的路径，但是右边的系统不是渗透的，不存在路径通过白色方块从上到下。  So the system at the left, you can find a way to get from the top to the bottom through white squares, but the system to the right does not percolate, there's no way to get from the top to the bottom through white squares.  

这是很多系统的模型。  So, that's a model for many systems.  

你可以想到电。  You can think of for electricity.  

你可以把白色方块想象成导体，把黑色方块想象成绝缘体。  You could think of a vacant site as being a conductor and, and a block site as being insulated.  

所以如果从上到下有一个导体，那么这个东西就会导电。  And so if there's a conductor from top to bottom then the thing conducts electricity.  

或者，你可以把它想象成，水流经某种多孔物质。  Or, you could think of it as, as water flowing through a porous substance of some kind.  

白色方块对应空，黑色方块对应有一些物质，水要么从上到下流过，要么不流过。  Where a vacant side is just empty and a block side has got some material, and either the water flows through from top to bottom, or not.  

或者你可以将其想象成一个社交网络，它是人们相互联系的，两个人之间要么有联系，要么没有联系，这是一种不通过社交网络从一群人到另一群人交流的方式。  Or you could think of a social network where it's people connected and either there's a c onnection between two people or not and these are a way not to get from one group of people to another communicating through that social network.  

这只是渗透模型的几个例子。  That's just a few examples of the percolation model.  

所以如果我们讨论的是一个随机模型，在这个模型中，站点以给定的概率是空的。  So if we, we are talking abouta randomized model where the sites are vacant with the given probability.  

所以很明显，如果它。  And so it's pretty clear that if it's.  

空位的概率很低，如图所示，图中左边的两个例子，它不会渗透。  Probability that a site is vacant is low as on the left, two examples on the left in this diagram, it's not going to percolate.  

没有足够的开放场地，从顶部连接到底部。  There's not enough open site for there to be a connection from the top to the bottom.  

如果概率很高，而且有很多开放的面，它肯定会渗透。  If the probability is high and there is a lot of open sides, it definitely is going to percolate.  

从顶部到底部有很多方法。  There would be lots of ways to get from the top to the bottom.  

但在中间，当它是中等的时候，它是否会渗透是有问题的。  But in the middle, when it's medium, it's questionable whether it percolates or not.  所以这个模型的科学问题，或者数学问题是，我们怎么知道，它是否会渗透?  So the scientific question, or the, mathematical question from this model is, how do we know, whether it's going to percolate or not?  

在这个问题和很多类似的问题中，都有所谓的相变。  In this problem and in many similar problems, there's what's called a phase transition.  也就是说，当它很低的时候，它不会渗透。  Which says that, you know, when it's low, it's not going to percolate.  当温度高时，它会渗透。  When it's high, it is going to percolate.  

实际上，它渗透和不渗透之间的阈值是非常明显的。  And actually, the threshold between when it percolates and when it doesn't percolate is very sharp.  实际上，当N变大时，有一个值如果小于这个值，它几乎肯定不会渗透，如果大于这个值，它几乎肯定会渗透。  And actually there is a value as N gets large that if you're less than that value it almost certainly will not percolate, if you're greater it almost certainly will.  问题是这个值是多少。  The question is what is that value.  

这是一个数学模型的例子，其中的问题非常清晰。  This is an example of a mathematical model where the problem is, is very well articulated.  

这个阈值是多少，但是没有人知道这个数学问题的解。  What's that threshold value but, nobody knows the solution to that mathematical problem.  我们唯一的解决方案来自于一个计算模型，在这个模型中，我们运行模拟来尝试确定概率的值。  The only solution we have comes from a computational model, where we run simulations to try and determine the value of that probability.  

这些模拟只有通过快速并集查找算法才能实现，这就是为什么我们需要快速并集查找算法的激励例子，让我们来看一下。  And those simulations are only enable by fast union find algorithms, that's our motivating example for why we might need fast union find algorithms, so let's look at that.  我们要运行的是所谓的蒙特卡罗模拟。  So what we're going to run is called a so called Monte Carlo simulation.  我们将整个网格初始化为黑色，然后随机填充开放的区域。  Where we initialize the whole grid to be block ed all black and then we randomly fill in open sites.  然后继续。  And we keep going.  

每次我们添加一个开放的站点，我们都会检查它是否会使系统渗透。  And every time we add an open site, we check to see if it makes the system percolate.  这样一直进行下去，直到系统开始渗透。  And we keep going until we get to a point where the system percolates.  

我们可以看到，渗透时的空位百分比是这个阈值的估计值。  And we can show that the vacancy percentage at the time that it percolates is an estimate of this threshold value.  

所以我们要做的就是把这个实验进行数百万次，我们可以在电脑上做，只要我们能，有效地计算它是否会渗透。  So what we want to do is run this experiment millions of times, which we can do in a computer, as long as we can, efficiently do the calculation of does it percolate or not.


这是一个蒙特卡罗模拟，一个计算问题给了我们一个解决方案，一个科学问题，一个没有人知道如何解决的数学问题。 That's a Monte Carlo simulation, a computational problem that gives us a solution to this, scientifc problem where, mathematical problems nobody knows how to solve yet. 

让我们更详细地看一下我们将如何使用动态连接模型来做这个。  So, let's, look in a little bit more detail of how we're going to use our dynam-, dynamic connectivity model to do this. 

因此，很明显，我们将创建与每个站点对应的对象。  So, it's clear that, we'll create an object corresponding to each site. 我们给它们取个名字，从0到N^2-1。  And we'll give'em a name, from zero to N^2-1 as indicated here. 然后把它们连在一起。  And then we'll connect them together. 如果它们是通过开放站点连接的。  If they're connected by open sites. 

所以左边的渗透模型对应右边的连接模型，根据我们一直在做的。  So the percolation model on the left corresponds to the, connection model on the right, according to what we've been doing. 

现在，你可能会说，我们要做的是，连接，检查下一行的任何站点是否连接到上一行的任何站点，然后使用并并查找。  Now, you might say, well, what we want to do is, connect, check whether any site in the bottom row is connected to any site in the top row, and use union find for that. 问题是，这是一个蛮力算法。  Problem with that is, that would be a brute force algorithm. 

从表面上看，应该是二次的。  Would be quadratic, right on the face of it. 因为它需要N^2，来检查它们是否连通。  Because it would have N^2, calls to find, to check whether they're connected. 

对于顶部的每个站点，我会检查底部的每个站点。  For each site on the top, I'd check each site on the bottom. 太慢了。  Much too slow. 相反，我们所做的是在顶部和底部创建一个虚拟站点。  Instead, what we do is create a virtual site on the top and on the bottom. 然后，当我们想知道这个系统是否渗透时，我们只需要检查虚拟的顶部位置是否连接到虚拟的底部位置。  And then, when we want to know whether this system percolates, we just check whether the virtual top site is connected to the virtual bottom site. 

那么我们如何建立一个新网站的模型呢?  So how do we model opening a new site? 要打开一个点，我们只要把它和相邻的点连接起来。  Well to open a site we just connect it to all it's adjacent open sites. 

这是对Union的一些调用，但这很容易实现。  So that's a few calls to Union but that's easy to implement. 然后，有了这个简单的关系，我们就可以使用我们开发的代码来继续运行这个连接问题的模拟。  And then with that, simple, relationship we can use the exactly the code that we developed to go ahead and run a simulation for this connectivity problem. 

这就是我们得到的结果，通过对足够大的n进行足够多的模拟，这个，渗透阈值大约是。592746。  And that's where we get the result that, by running enough simulations for a big-enough n, that this, percolation threshold is about .592746. 

通过这种快速算法，我们可以得到科学问题的准确答案。  With this fast algorithm we can get an accurate answer to the scientific question. 如果我们使用慢速的并集算法，我们就不能在大问题上运行它，我们也不会得到一个非常准确的答案。  If we use a slow Union-find algorithm we won't be able to run it for very big problems and we won't get a very accurate answer. 

总结一下，我们选择了一个重要的问题。  So in summary, we took an important problem. 动态连接问题。  The, the dynamic connectivity problem. 我们对这个问题进行建模，试图准确地理解我们需要什么样的数据结构和算法来解决它。  We modeled the problem to try to understand precisely what kinds of data structures and algorithms we'd need to solve it. 

我们看到一些简单的算法可以解决问题，但很快就发现它们不足以解决大问题。  We saw a few easy algorithms for solving the problem, and quickly saw that they were inadequate for addressing huge problems. 然后我们看到了如何改进它们来得到更有效的算法。  But then we saw how to improve them to get efficient algorithms. 

然后留给我们的是，没有这些有效的算法就无法解决的应用。  And then left us with, applications that, could not be solved without these efficient algorithms. 所有这些都涉及到科学方法。  All of this involves the scientific method.

对于算法设计，我们试图建立数学模型来帮助我们理解我们正在开发的算法的特性。  For algorithm design where we try to develop mathematical models that help us understand the properties of the algorithms that we're developing.

然后我们通过实验来测试这些模型使我们能够改进算法迭代，开发更好的算法和更精细的模型直到我们得到我们需要的来解决我们感兴趣的实际问题。  And then we test those models through experimentation enabling us to improve algorithms iterating, developing better algorithms and more refined models until we get what we need to solve the practical problems that we have of interest. 

这将是学习算法的总体架构我们将在整个课程中用到。  That's going to be the overall architecture for studying algorithms that we're going to use throughout the course.