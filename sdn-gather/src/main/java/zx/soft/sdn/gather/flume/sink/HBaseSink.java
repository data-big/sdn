package zx.soft.sdn.gather.flume.sink;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HBase接收器
 * 
 * @author xuran
 *
 */
public class HBaseSink extends AbstractSink implements Configurable {

	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory.getLogger(HBaseSink.class);

	@Override
	public Status process() throws EventDeliveryException {

		Status status = null;
		Channel channel = getChannel();
		Transaction transaction = channel.getTransaction();
		transaction.begin();
		try {
			Event event = channel.take();
			logger.debug("==================" + new String(event.getBody()) + "==================");
			transaction.commit();
			status = Status.READY;
		} catch (Throwable t) {
			transaction.rollback();
			status = Status.BACKOFF;
			if (t instanceof Error) {
				throw (Error) t;
			}
		} finally {
			transaction.close();
		}
		return status;

	}

	@Override
	public void configure(Context context) {
	}

}
