public class EndTermAda {
    static class Item {
        int value, weight;
        Item(int x, int y){
            this.value = x;
            this.weight = y;
        }
    }

    public static void display(double arr[])
    {
        for(int i=0;i<arr.length;i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static double  fractionalKnapsack(int W, Item arr[], int n) {
        int size=arr.length;
        double []PbyWArr=new double[size];
        double []objectUsed=new double[size];
        int containerLimit=W;
        double maxProfit=0.0;

        for(int i=0;i<size;i++)
        {
            PbyWArr[i]=arr[i].value/arr[i].weight;
        }

        while(containerLimit>0)
        {
            double max=0.0;int index=0;
            for(int i=0;i<size;i++)
            {
                if(PbyWArr[i]>max)
                {
                    max=PbyWArr[i];
                    index=i;
                }
            }

            PbyWArr[index]=0.0;
            if(arr[index].weight<containerLimit)
            objectUsed[index]=1.0;
            else
            objectUsed[index]=(double)containerLimit/arr[index].weight;
            containerLimit-=objectUsed[index]*(arr[index].weight);
        }
        
        for(int i=0;i<objectUsed.length;i++)
        {
            maxProfit+=objectUsed[i]*arr[i].value;
        }
        return maxProfit;
    }

    public static void main(String []args)
    {
        Item arr[]={new Item(60,10),new Item(100,20),new Item(120,30)};
        System.out.println(fractionalKnapsack(50, arr, 3));
    }
}
