package src.main.threads;

import java.util.LinkedList;

import src.util.KFileReader;
import src.util.KFileWriter;

public class LoaderThread implements Runnable {

	/**
	 * LoadJob:		contains details of a resource which needs to be
	 * 				loaded by the loader thread.
	 */
	private class LoadJob {
		String resPath;
		ResType resType;
		
		/**
		 * LoadJob:		creates a load job object.
		 * 
		 * @param resPath
		 * @param resType
		 */
		LoadJob(String resPath, ResType resType) {
			this.resPath = resPath;
			this.resType = resType;
		}
	};
	
	// KF READING/WRITING CLASSES
	private final KFileReader kr = new KFileReader();
	
	private enum ResType {
		LOAD_EPISODE,
		LOAD_MAP,
		LOAD_TILE_REGISTRY,
		LOAD_OBJECT_REGISTRY,
		LOAD_ENTITY_REGISTRY
	};
	private Thread loaderThread;
	private LinkedList<LoadJob> loadJobList = new LinkedList<>();
	
	public void queLoadJob() {
		
	}
	
	/**
	 * startLoaderThread:		starts the Loader thread
	 */
	public void startLoaderThread() {
		loaderThread = new Thread(this);
		loaderThread.start();
	}

	/**
	 * stopLoaderThread:		stops the Loader thread
	 */
	public void stopLoaderThread() {
		loaderThread = null;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (loaderThread != null) {
			if (!loadJobList.isEmpty()) {
				System.out.println("Job exists");
			}
		}
	}
}