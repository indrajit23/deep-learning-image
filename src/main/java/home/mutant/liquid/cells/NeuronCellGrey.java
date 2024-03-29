package home.mutant.liquid.cells;

import home.mutant.deep.utils.MathUtils;

public class NeuronCellGrey extends NeuronCell 
{
	public NeuronCellGrey(int noSynapses) 
	{
		super(noSynapses);
	}
	public double output(byte[] pixels)
	{
		double sum=0;
		for (int i = 0; i < pixels.length; i++) 
		{
			int pixel = pixels[i];
			if (pixel<0)pixel+=255;
			double weight = weights[i];
			if (weight==0) weight=-150;
			sum+=(pixel)*weight;
		}
		return sum;
	}
	public boolean isFiring(byte[] pixels)
	{
		output = output(pixels);
//		System.out.println(output);
//		System.out.println(threshold);
//		System.out.println();
		return output>=threshold;
	}
	public void modifyWeights(byte[] pixels)
	{
		for (int i = 0; i < pixels.length; i++) 
		{
			int pixel = pixels[i];
			if (pixel<0)pixel+=255;
			weights[i]=(weights[i]+pixel);
		}
		threshold = MathUtils.sumSquared(weights);
	}
	public double getDistanceFromImage(byte[] pixels)
	{
		output = output(pixels);
		return threshold - output;
	}
	public double output(double[] pixels)
	{
		double sum=0;
		for (int i = 0; i < pixels.length; i++) 
		{
			double weight = weights[i];
			if (weight==0) weight=-150;
			sum+=(pixels[i])*weight;
		}
		return sum;
	}
	public double getDistanceFromNeuron(NeuronCell neuron)
	{
		output = output(neuron.weights);
		return output;
	}
	@Override
	public double output(NeuronCell neuron)
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
